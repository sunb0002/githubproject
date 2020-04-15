import React from 'react';

/**
 * Note1: To use default provider value, need to remove the explicit <Provider/> tag in the code.
 * Note2: To choose between Context and Redux:
 *      - Redux provides more complex features, like data flow actions, devtools. Context is for simple apps.
 *      - If consumer wants to modify data source, need to write data-update function in context provider.
 *        This is a bit troublesome compared with Redux.
 */
const UserContext = React.createContext("some default value");

const UserProvider = UserContext.Provider;
const UserConsumer = UserContext.Consumer;

export { UserContext, UserProvider, UserConsumer };
