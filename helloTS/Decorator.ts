class IceCream {
    toppings = [];

    @Emoji()
    flavor = "vanilla";
    @Portable("Message!")
    addTopping(top: string) {
        this.toppings.push(top);
    }
}

const ic = new IceCream();
ic.addTopping(ic.flavor);
ic.addTopping("sparkle");
console.log(ic.toppings);

// Property
function Emoji() {
    // key: annotated property name; target: class-object that the key belongs to.
    return function (target: any, key: string) {
        let val = target[key];
        const getter = () => val;
        const setter = (next: string) => {
            val = `~${next}~`;
        };
        Object.defineProperty(target, key, {
            get: getter,
            set: setter,
        });
    };
}

// Method
function Portable(msg: string) {
    // key: annotated method name; target: class-object that the key belongs to.
    return function (
        target: IceCream, // specified class type
        key: string,
        descriptor: PropertyDescriptor
    ) {
        const original = descriptor.value;
        descriptor.value = function (...args: any[]) {
            const revArgs = (args || []).map((arg) =>
                arg.split("").reverse().join("")
            );
            const result = original.apply(this, revArgs);
            return result;
        };
        return descriptor;
    };
}
