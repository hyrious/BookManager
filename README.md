# BookManager

[中文文档](README-zh.md)

Java course work using [sqlite][1]-[jdbc][2], [JavaFX][3].

## How to test

I'm working on Windows, not considering other OS.

The test process executes `ruby classes.rb bin` to find all classes in the
`bin` folder, which means it needs `ruby` in your path, otherwise bat will
throw out an error but not stop testing.

Open `cmd` at this repo's root path, then

    batake test

It will compile all java files in `src` folder to `bin` folder,
then ask you to specify a class name to enter its `main()` method.
The class name should include package name, e.g. `bkmgr.DataBase`.

## Folder Structure

    draft/    # design/unfinished-documentation
    test/     # code-snippets/test-apis
    src/      # source code

## Limitation

As you can see, the database file is transparent, lacking of safety.

## License

MIT.


[1]: https://sqlite.org
[2]: https://github.com/xerial/sqlite-jdbc
[3]: https://en.wikipedia.org/wiki/JavaFX
