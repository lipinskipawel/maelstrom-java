# Unofficial java client for [Maelstrom] server

[Maelstrom]: https://github.com/jepsen-io/maelstrom

## How to release maelstrom-java library

Release process is semi-automated. It begins by adding a tag version (e.g. `v0.1.0`) to a specific commit and assigning
version to `version` in `build.gradle.kts`. Tagged commit will be release as a new version of library.

Next release have to be triggered manually by Github action release.

After that Github workflow will react on the release and publish library to Maven Central Repository.

## License
This project is [MIT] licensed.

[MIT]: LICENSE
