require_relative 'tinydsl'

class Var
  def self.[](x)
    new x
  end
  def initialize x
    @x = x
  end
  def inspect
    @x.to_s
  end
end

puts TinyDSL.new {
  def connect(_, &blk)
    _safe(
      'connection = DriverManager.getConnection("jdbc:sqlite:sample.db");' +
      instance_exec(&blk).join("\n")
    )
  end
  def sql(*strs)
    [
      'Statement statement = connection.createStatement();', 
      'statement.setQueryTimeout(30);',
      *strs.map { |e| "statement.executeUpdate(#{e.inspect});" }
    ]
  end
  def _safe(try, cat = println(Var[:e]), fin = '')
    [
      "try {",
        try,
      "} catch (Exception e) {",
        cat,
      "} finally {",
        fin,
      "}"
    ]
  end
  def println str
    "System.out.println(#{str.inspect});"
  end
}.run {
  connect 'sqlite' do
    sql "drop table if exists person", "create table person (id integer, name string)"
  end
}.result
