# Contributing

## Development branches

This repo is using two development branches

- `APP_DEV` for client-side app development
- `BACKEND_DEV` for server development

## Git flow

We use git flow with feature/bug branches merged to development branches. Therefore, pull requests are supposed to be submitted with one of development branches as a target branch.

## Branches naming convention

We use branch naming convention with group tokens as prefix. Please apply to these while contributing. The prefixes used are as following:

|   Prefix    |        Content         |          Example           |
| :---------: | :--------------------: | :------------------------: |
|   `feat/`   |      New feature       | `feat/json-serialization`  |
|   `bug/`    | Fixing an existing bug | `bug/register-redirection` |
| `refactor/` | Refactoring code       | `refactor/register-form`   |
| `hotfix/`\* |      Hot fixes to      | `hotfix/user-profile-page` |

_\*We are not using `hotfix/` prefix until we start application versioning_

## Pull requests naming convention

Every opened pull request should have a title which consists of prefix followed by a colon, space and short description indicating what changes the pull requests introduces, where prefixes available are:

|   Prefix   |        Content         |                     Example                     |
| :--------: | :--------------------: | :---------------------------------------------: |
| `Feature:` |      New feature       |        `Feature: Add JSON serialization`        |
| `Bugfix:`  | Fixing an existing bug | `Bugfix: Add proper redirection after register` |
| `Hotfix:`  |      Hot fixes to      |     `Hotfix: Fix user profile page button`      |
