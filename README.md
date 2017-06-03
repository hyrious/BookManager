# BookManager

Java course work using [JavaFX][3], [sqlite][1]-[jdbc][2] and [lombok][5].

## Information

It's a typical [eclipse][4] project.

It's source code is encoded UTF-8, where Unicode strings (in Chinese) are hard
coded, and it's about to be submitted to my Java teacher in China.

## Development

Feel free to pull request or raise an issue.

### Requirements

To meet development environment, you should:

0. install e(fx)clipse tools
1. put [sqlite-jdbc][2] and [lombok][5] jars into this project's root folder, open eclipse
2. double click lombok.jar from eclipse package manager view to install it, then restart eclipse
3. add sqlite-jdbc.jar and lombok.jar into this project's building path
4. **Project -> Clean...** then **Run**

If eclipse won't run/debug correctly, try **Project -> Clean...** and **Build All**.

### Is anything missing?

Default SuperAdmin's account is (sa, sa).

## Limitation

As you can see, the database file is transparent, lacking of safety.

## Useful links

- [fx experience][8]
- [JavaFX Scene Builder][6]
- [(java.sql) DBUtils][7]

## License

MIT.

[1]: https://sqlite.org
[2]: https://github.com/xerial/sqlite-jdbc
[3]: https://en.wikipedia.org/wiki/JavaFX
[4]: https://www.eclipse.org
[5]: https://projectlombok.org
[6]: https://gluonhq.com/products/scene-builder
[7]: https://github.com/zhoumengkang/java/blob/master/java-pre-sql/DBUtils.java
[8]: http://fxexperience.com/
