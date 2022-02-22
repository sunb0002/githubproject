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
type BugFixTask = Task<TaskType.BUGFIX>;
const feature1: FeatureTask = { name: "new button", type: TaskType.FEATURE };
// feature1.type = TaskType.BUGFIX; // ❌
console.log(feature1);
// -- Functions' Generic Type Variables: no need to specify type, auto inference
const swapTask = <T1, T2>(t1: T1, t2: T2): [T2, T1] => [t2, t1];
const swapTaskResult = swapTask(feature1, feature1); // [FeatureTask, FeatureTask]

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
type PowerTrip = TripWithInfo | { to: number }; // overwriting type field
const trip3: PowerTrip = { from: "chengdu", to: 3 };

// --
// -- Typed Destructure (syntax)
const [trip4, trip5]: [TripWithID, object] = [{ uuid: 10 }, { uuid: 11 }];

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
const trip9: unknown = { uuid: 11 };
isTripWithID(trip9) && console.log("trip9 is TripWithID.");
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
type TripFrom = Pick<TripWithInfo, "from" | "to">; // {from: string, to: string;}
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
// help to sanitize function input parameters if it's to manipulate an object by its keys
type ReleaseKeys = keyof Release; // "name" | "tasks"
const getReleaseProp = (P: ReleaseKeys) => {};

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

// --
// -- Mapped Types (Type computation. Creating new types by iteration through a union of keys.)
// We can easily use +,- to implement Readonly/Partial/Required utility types (built-in mapped types).
type Point = {
    x: number;
    y?: number;
};
type Readonly2<T> = {
    readonly [P in keyof T]-?: T[P];
};
type PointType2 = Readonly2<Point>; // both x,y are readonly and non-optional
type PointType3 = {
    -readonly [P in keyof Point]?: Point[P]; // both x,y are non-readonly and optional
};
// -- Mapped type with recursive (DeepReadonly)
type DeepReadonly<T> = {
    readonly [P in keyof T]: DeepReadonly<T[P]>;
};
type StarShip4 = DeepReadonly<StarShip>;
const starShip4: StarShip4 = { name: "ship3", tag: { age: 100 } };
// starShip4.tag.age = 101; // ❌ no longer shallow, compared with starShip3

// --
// -- Template Literal Type
// Very useful for restricting input parameters
type CSSValue = number | `${number}px` | `${number}rem`;
const paint = (css: CSSValue) => {};
paint("20px"); // ✅
// paint("20ex"); // ❌
type Size = "small" | "large";
type Color = "primary" | "secondary";
type MyStyle = `${Size}-${Color}`;
const myStyle1: MyStyle = "small-secondary"; // ✅
// const myStyle1: MyStyle = "small2-secondary"; // ❌

// --
// -- const assertion ("as const", helper for Union Types)
// Since TypeScript is just a compiler, none of the typing information is present at runtime.
// This means that unfortunately you cannot iterate through a type.
const helloVar = "hello" as const; // Type: '"hello"'
const names = ["Bill Gates", "Steve Jobs", "Linus Torvalds"] as const; // Type: readonly ['xx','xx']
type Names = typeof names[number]; // Type: 'xx'|'xx'
const companies = {
    "Bill Gates": "Microsoft",
    "Steve Jobs": "Apple",
    "Linus Torvalds": "Linux",
} as const; // Type: readonly {}
const ms1 = companies["Bill Gates"]; // Type: "Microsoft"
const ms2 = companies["Bill Gates2"]; // Type: any (companies's index was not strictly specified)

// --
// -- Typescript function overloading
// Must write in old function format. Must not have implementation for "sub" function.
// No overloading in Javascript.
function adder(a: number): number;
function adder(a: string): string;
function adder(a: any): any {
    return `a is ${a}`;
}
const adderResult1 = adder(1); // number
const adderResult2 = adder("1"); // string
