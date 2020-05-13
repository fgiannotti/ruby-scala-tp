require 'CustomTrait'

class Context
  def module_name(name)
    @created_trait = Object.const_set(name, CustomTrait.new)
  end

  def module_method(method_name, &block)
    @created_trait.define_singleton_method(method_name, block)
  end
end

class Trait
  def self.define(&block)
    Context.new.instance_eval &block
  end
end

class Class
  def uses(t)
    t.methods(false).each do
    |met|
      define_method(met) { |*args| t.method(met).call(*args) }
    end
  end
end

class Symbol
  def >>(other_symbol)
    [self, other_symbol]
  end
end