class App
  class Scope
    def button(&blk)
      "<Button>#{instance_exec(&blk)}</Button>"
    end
    def text(v = nil)
      "<Text>#{v}</Text>"
    end
  end
  attr_accessor :result
  def initialize
    @scope = Scope.new
    @result = []
  end
  def method_missing(*args, &blk)
    @result << Array(@scope.send(*args, &blk))
  end
end

def app(&blk)
  x = App.new
  x.instance_exec(&blk)
  x.result
end

puts app {
  button {
    text '1'
  }
}
