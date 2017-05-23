class TinyDSL
  class Proxy
    attr_accessor :obj, :ret
    def initialize(obj)
      @obj = obj
      @ret = []
    end
    def method_missing(*args, &blk)
      @ret += Array(@obj.send(*args, &blk))
    end
  end
  def initialize(&blk)
    @proxy = Proxy.new Class.new(&blk).new
  end
  def run(&blk)
    @proxy.instance_exec(&blk)
    self
  end
  def result
    @proxy.ret
  end
end

=begin # Test
x = TinyDSL.new do
  def int(opt = {})
    opt.map{|k,v|"int #{k} = #{v};"}
  end
  def println(str)
    "System.out.println(#{str.inspect});"
  end
end
x.run {
  int a: 3, b: 5
  println "Hello world"
}
puts x.result
# int a = 3;
# int b = 5;
# System.out.println("Hello world");
=end
