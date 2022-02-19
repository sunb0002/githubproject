// -- Generics
export enum TaskType {
    FEATURE,
    BUGFIX,
}
type Task<T = TaskType> = {
    name: string;
    type: T;
};
type FeatureTask = Task<TaskType.FEATURE>;
const feature1: FeatureTask = { name: "new button", type: TaskType.FEATURE };
// feature1.type = TaskType.BUGFIX; // ❌
console.log(feature1);

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
type Engine<T> = T extends { engine: any } ? T["engine"] : never;
type BusEngine = Engine<Bus>;
type CarEngine = Engine<Car>;
type BikeEngine = Engine<Bike>; // "never"
const eng1: BusEngine = { type: "bio" };
// const eng1: BusEngine = { type: "water" }; // ❌
// const eng1: BusEngine = { type: 90 }; // ❌
const eng2: CarEngine = { type: 90 }; // ✅
// const eng3: BikeEngine = { power: "human" }; // ❌ nothhing can be assigned to "never" type

// -- Type Exclude/Extract/NonNullable/ReturnType
type T01 = Extract<"A" | "B" | "C", "A" | "E">; // "A"
type T02 = Exclude<"A" | "B" | "C", "A" | "E">; // "B"|"C"
type T03a = T01 & T02; // never
type T03b = T01 | T02; // "A" | T02, but actually equals to "A"|"B"|"C"
const t03b: T03b = "B"; // ✅
type T04 = NonNullable<T01 | undefined>; // "A"
type T05 = ReturnType<typeof parseInt>; // number. ReturnType only accepts a function that infers a type

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
