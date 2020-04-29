class Context
  def nombre(n)
    @createdTrait = Object.const_set(n, Module.new)
  end

  def method(m)
    @createdTrait.define_singleton_method(m)
  end
end

class Trait
  def self.define(&block)
    Context.new.instance_eval &block
  end
end


