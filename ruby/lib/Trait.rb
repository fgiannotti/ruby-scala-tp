require 'CustomTrait'

class Context
  def trait_name(name)
    @created_trait = Object.const_set(name, CustomTrait.new)
  end

  def trait_method(method_name, &block)
    @created_trait.my_methods[method_name] = block
  end
end

class Trait
  def self.define(&block)
    Context.new.instance_eval &block
  end
end

class Class
  def uses(t)
    t.my_methods.each {
    |met, body|
      define_method(met) { |*args| body.call(*args) }
    }
  end
end

class Symbol
  def >>(other_symbol)
    [self, other_symbol]
  end
end