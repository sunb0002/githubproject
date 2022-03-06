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

// Property Anno: Manipulate the property's descriptors
function Emoji() {
    // key: annotated property name ("flavor")
    // target: instance of that class (ic)
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

// Method Anno: Manipulate "descriptor.value"
function Portable(msg: string) {
    // key: annotated method name ("addTopping")
    // target: instance of that class (ic)
    return function (
        target: IceCream, // specified class type (can't be reused by other classes)
        key: string,
        descriptor: PropertyDescriptor
    ) {
        console.time(msg);
        const original = descriptor.value;
        descriptor.value = function (...args: any[]) {
            const revStrFn = (s: string) => s.split("").reverse().join("");
            const revArgs = (args || []).map(revStrFn);
            const result = original.apply(this, revArgs);
            return result;
        };
        console.timeEnd(msg);
        return descriptor;
    };
}
