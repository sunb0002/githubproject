// -- Generally, big teams/projects prefer Interface over Type.
// functional programming users use "type", object-oriented programing users choose "interface"
// =========SIMILARITY=================
// -- Interface extension / Type intersection
interface PartialPointX {
    x: number;
}
interface Point extends PartialPointX {
    y: number;
}
type Point2 = PartialPointX & { y: number };
const point1: Point = { x: 1, y: 2 };
const point2: Point2 = point1;
console.log(point1, point2);

// =========DIFFERENCE=================
// -- Declaration Merging: only Interface can, Type alias cannot.
// very important for Nodejs Middleware design pattern.
interface User {
    name: string;
}
interface User {
    age: number;
}
const user1: User = { name: "u1", age: 1 };
// (1) const user1: User = { name: "u1", age: 1, adsf: 2 }; // ❌, no extra field allowed
// (2) const user1 = { name: "u1", age: 1, adsf: 2 } as User;
// ✅, but not good.
// Like a type annotation, type assertions are removed by the compiler and won’t affect the runtime behavior.
// You can also use the angle-bracket syntax (except in .tsx file), which is equivalent
// (2.5) const user2 = { name: "u1", age: "a" } as User;
// ❌, type assertions only prevents "impossible" coercions.
console.log(user1);

// -- Union Type and iteration/computation/lookup: Type can, Interface cannot.
// Type computations help to avoid type noise/pollutions.
type ID = string | number;
const id: ID = 1;
// const id: ID = true; // ❌
type keys = "firstName" | "lastName";
type Name = {
    [key in keys]: string;
};
const name1: Name = {
    firstName: "undefined",
    lastName: "undefined",
};
console.log(name1);
type Route = {
    from: {
        city: string;
        state: string;
    };
    to: {
        city: string;
        province: string;
    };
};
type USCity = Route["from"];
type CNCity = Route["to"];
const city1: CNCity = {
    city: "chengdu",
    province: "sichuan",
};
// const city2: USCity = { city: "chengdu", province: "sichuan" }; // ❌

// -- Class Implements: Interface/Class/Type are static blueprints, but Union Type is not
class NameClazz implements Name {
    firstName: string;
    lastName: string;
}
// class IDClazz implements ID {} // ❌

// =========MISC=================
// -- Interface for function
interface searchFunc {
    (fullStr: string, subStr: string): boolean;
}
const searchFn: searchFunc = (full: string, sub: string) => true;
// const searchFn: searchFunc = (full: string, sub: number) => true; // ❌
// const searchFn: searchFunc = (full: string, sub: string, s: string) => true; // ❌

// -- Tuple type
type IDCard = [string, number];
const myID: IDCard = ["name", 1];
// const myID: IDCard = ["name", 1, 2]; // ❌
