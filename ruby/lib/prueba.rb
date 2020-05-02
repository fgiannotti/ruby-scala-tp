class CustomTrait
  def +(another_trait)

    myTraitMethods = self.methods(false)
    myTrait = self
    Trait.define do
      t = module_name :Algo

      another_trait.methods(false).each do
      |met| module_method(met){|*args| another_trait.method(met).call(*args)}
      end
      myTraitMethods.each do
      |met| module_method(met){|*args| myTrait.method(met).call(*args)}
      end
      t
    end

  end
end

class Context
  def module_name(name)
    @created_trait = Object.const_set(name, CustomTrait.new)
  end

  def module_method(method_name, &block)
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
