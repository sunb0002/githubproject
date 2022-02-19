// --
// -- Generics: T is a variable in our type system
export enum TaskType {
    FEATURE,
    BUGFIX,
}
// T default to TaskType. Also can use <T extends TaskType>
type Task<T = TaskType> = {
    name: string;
    type: T;
};
type FeatureTask = Task<TaskType.FEATURE>;
const feature1: FeatureTask = { name: "new button", type: TaskType.FEATURE };
// feature1.type = TaskType.BUGFIX; // ❌
console.log(feature1);

// --
// -- Type Extraction
type Trip =
    | {
          from: string;
          to: string;
      }
    | {
          uuid: number;
      };
type TripWithInfo = Extract<Trip, { from: string }>; // match by any one field
type TripWithID = Extract<Trip, { uuid: number }>;
const trip1: TripWithInfo = { from: "chengdu", to: "chongqing" };
// const trip1: TripWithInfo = { from: "chengdu" }; // ❌ missing field
const trip2: TripWithID = { uuid: 10 };
console.log(trip1, trip2);

// --
// -- Type Predicate: a function which tells TS compiler to narrow down a variable's type
// -- "unknown" type: force developers to narrow down. The opposite of "any".
function isNumberArray(x: unknown): x is number[] {
    return Array.isArray(x) && x.every((v) => typeof v === "number");
}
const xArr: unknown = [1, 2];
if (isNumberArray(xArr)) {
    const nArr: number[] = xArr; // ✅ xArr is narrowed down to number[]
} else {
    // const nArr: number[] = xArr; // ❌ xArr is still unknown
}
const isTripWithID = (trip: unknown): trip is TripWithID =>
    typeof trip === "object" && "uuid" in trip;
const trip3: unknown = { uuid: 11 };
isTripWithID(trip3) && console.log("trip3 is TripWithID.");
console.log(typeof isTripWithID); // "function"

// --
// -- Conditional Types
// -- "never" type: always throw error when assigned
type Diesel = {
    type: "petro" | "bio" | "synthetic";
};
type Gasoline = {
    type: 90 | 93;
};
type Bus = {
    engine: Diesel;
};
type Car = {
    engine: Gasoline;
};
type Bike = {
    power: "human";
};
type Engine<T> = T extends { engine: any } ? T["engine"] : never; // "extends" is "===" at type level.
type BusEngine = Engine<Bus>;
type CarEngine = Engine<Car>;
type BikeEngine = Engine<Bike>; // "never"
const eng1: BusEngine = { type: "bio" };
// const eng1: BusEngine = { type: "water" }; // ❌
// const eng1: BusEngine = { type: 90 }; // ❌
const eng2: CarEngine = { type: 90 }; // ✅
// const eng3: BikeEngine = { power: "human" }; // ❌ nothhing can be assigned to "never" type

// --
// -- Type Exclude/Extract/NonNullable/ReturnType (deal with Union types)
type T01 = Extract<"A" | "B" | "C", "A" | "E">; // "A"
type T02 = Exclude<"A" | "B" | "C", "A" | "E">; // "B"|"C"
type T03a = T01 & T02; // never
type T03b = T01 | T02; // "A" | T02, but actually equals to "A"|"B"|"C"
const t03b: T03b = "B"; // ✅
type T04 = NonNullable<T01 | null | undefined>; // "A"
type T05 = ReturnType<typeof parseInt>; // number. ReturnType only accepts a function which infers a type.

// --
// -- Pick / Omit (deal with keys/properties)
type TripFrom = Pick<TripWithInfo, "from">; // {from: string;}
type TripTo = Omit<TripWithInfo, "from">; // {to: string;}

// --
// -- InstanceType (get class's constructor's return type)
// -- Parameters (get function's parameters' type as type[])
class Ship {
    name: string;
    drive() {}
}
type ShipInstanceType = InstanceType<typeof Ship>; // typeof Ship = "function"
function shipFn(a: number, b: string) {}
type shipFnParaType = Parameters<typeof shipFn>; // [a: number, b: string]

// --
// -- Type inference
// quite useful for GraphQL
type Unarray<T> = T extends Array<infer U> ? U : never;
type Tinf1 = Unarray<string[]>; //"string"
const backlog = {
    releases: [
        {
            name: "Sprint 1",
            tasks: ["A", "B", "C"],
        },
    ],
};
type Release = Unarray<typeof backlog["releases"]>;

// --
// -- keyof: get the String-Literal-Union of type/plain-object keys
type ReleaseKeys = keyof Release; // "name" | "tasks"

// --
// -- Partial: make all fields optional
// -- Required: make all fields mandatory
interface StarShip {
    name: string;
    tag: { age: number };
}
type StarShip1 = Partial<StarShip>; // {name?: string;}
type StarShip2 = Required<StarShip1>; // {name: string;}

// --
// -- Readonly: all fields readonly, i.e, cannot overwrite
type StarShip3 = Readonly<StarShip>;
const starShip3: StarShip3 = { name: "ship3", tag: { age: 100 } };
// starShip3.name = "another name"; // ❌ name is readonly field
starShip3.tag.age = 101; // ✅ readonly is shallow

// --
// -- Record (just a plain object with specified key-type)
const starShipRecords: Record<number, StarShip> = {
    1: starShip3,
    2: starShip3,
};
starShipRecords[3] = starShip3;
console.log(starShipRecords);

// --
// -- Intrinsic String Manipulation Types
// only changes case for type names, not for instances/variables
type T09 = "A" | "b" | "cd" | "1";
type UString = Uppercase<T09>; // "A" | "B" | "1" | "CD"
type LString = Lowercase<T09>; // "b" | "cd" | "1" | "a"
type CString = Capitalize<T09>; // "A" | "B" | "1" | "Cd"
