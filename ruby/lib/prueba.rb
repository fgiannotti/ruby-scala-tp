class CustomTrait
  def initialize
    super
  end
  def self.+(another_trait)
    another_trait.instance_methods(false).each do
    |met| instance_eval do
      define_method(met){|*args| another_trait.method(met).call(*args)}
    end
    end
  end
end

class Context
  def trait_name(name)
    @created_trait = Object.const_set(name, CustomTrait)
  end

  def method(method_name, &block)
    @created_trait.class_eval do
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
    t.each do
        |met| define_method(met){|*args| t.method(met).call(*args)}
    end
  end
end

