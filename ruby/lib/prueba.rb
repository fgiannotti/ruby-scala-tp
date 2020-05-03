class CustomTrait

  def initialize(new_methods =Hash.new)
    new_methods.each do |met, block|
      define_singleton_method(met, block)
    end
  end

  def +(another_trait)
    new_methods = Hash.new
    my_methods = self.methods(false)
    another_methods =  another_trait.methods(false)
    conflict_method_names = my_methods & another_methods
    [self, another_trait].each { |trait|
      (trait.methods(false)).map{ |met|
        proc = if conflict_method_names.include? met
                 Proc.new{ raise ConflictMethodError.new}
               else
                 Proc.new{|*args| trait.method(met).call(*args)}
               end
        new_methods[met]= proc
      }
    }
    CustomTrait.new new_methods
  end
end

class Context
  def module_name(name)
    @created_trait = Object.const_set(name, CustomTrait.new )
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
    |met| define_method(met){|*args| t.method(met).call(*args)}
    end
  end
end

class ConflictMethodError < StandardError
  def initialize(msg="Can't execute the message cause has conflict")
    super
  end
end