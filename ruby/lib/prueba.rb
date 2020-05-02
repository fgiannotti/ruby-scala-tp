class CustomTrait
  def self.+(another_trait)
    another_trait.methods(false).each do
      |met| instance_eval do
        define_method(met){|*args| another_trait.method(met).call(*args)}
      end
    end
  end
end

class Context
  def module_name(name)
    @created_trait = Object.const_set(name, CustomTrait.new)
  end

  def method(method_name, &block)
    @created_trait.instance_eval do
      define_singleton_method(method_name, block)
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
    t.methods(false).each do
        |met| define_method(met){|*args| t.method(met).call(*args)}
    end
  end
end
