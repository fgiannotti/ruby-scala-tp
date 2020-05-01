class Context
  def module_name(name)
    @created_trait = Object.const_set(name, Module.new)
  end

  def method(method_name, &block)
    @created_trait.module_eval do
      define_method(method_name, block)
    end
  end
end

class Trait
  def self.define(&block)
    Context.new.instance_eval &block
  end
end

class Class
  def uses(t)
    include(t)
  end
end

