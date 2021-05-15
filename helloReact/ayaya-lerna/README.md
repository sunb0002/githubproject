# Ayaya (Ayanami)

Ayaya uses this monorepo scaffold for frontend projects.
* [Tools](#Tools)
* [Useful Commands](#Useful-Commands)

## Tools
* React (CRA)
* Rollup ([rollup-starter-lib](https://github.com/rollup/rollup-starter-lib/tree/typescript))
* [Storybook](https://github.com/storybookjs/storybook)
* [Lerna](https://github.com/lerna/lerna) (yarn)

## Useful Commands
- To list repos:
    `lerna ls -la`
    `lerna ls --graph`
- To add my-lib to my-app (one-time)
    `lerna add sunb0002-my-lib --scope=sunb0002-my-app`
- To publish latest packages
    `git commit ...`
    `lerna updated`
    `lerna publish`
- To pull latest packages
    `lerna exec -- yarn upgrade --latest` or 
    `npm run update`
- To run my-lib storybook:
    `npm run sb`

