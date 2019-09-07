# Using express-generator 

### Useful Commands
```
npm start
```

### Service Url
```
http://localhost:3000
http://localhost:3000/pray
http://localhost:3000/qb-not-found
```

### Lessons Learned
```
1. NodeJS file name convention: kebab-case, not snake_case or camelCase
2. ES6 await must be used within async functions. Different from TypeScript.
```


### Folder Structure
```
.
├── README.md
├── app.js
├── bin
│   └── www
├── models
│   ├── general-response.js
│   └── mahou-shoujo.js
├── package-lock.json
├── package.json
├── public
│   └── assets
│       ├── qbey.jpg
│       └── style.css
├── routes
│   ├── incubator.js
│   ├── index.js
│   └── prayer.js
└── views
    ├── error.ejs
    └── index.ejs
```