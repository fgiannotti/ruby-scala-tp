class CustomTrait

  def initialize(new_methods = Hash.new)
    new_methods.each do |met, block|
      define_singleton_method(met, block)
    end
  end

  def +(another_trait)
    new_methods = Hash.new
    my_methods = self.methods(false)
    another_methods = another_trait.methods(false)
    conflict_method_names = my_methods & another_methods
    [self, another_trait].each { |trait|
      (trait.methods(false)).map { |met|
        proc = if conflict_method_names.include? met
                 Proc.new { raise ConflictMethodError.new }
               else
                 Proc.new { |*args| trait.method(met).call(*args) }
               end
        new_methods[met] = proc
      }
    }
    CustomTrait.new new_methods
  end

  def -(met_symbol)
    new_methods = Hash.new
    methods(false).reject { |met| met.equal? met_symbol}.each do |met|
      proc = Proc.new { |*args| self.method(met).call(*args) }
      new_methods[met] = proc
    end
    CustomTrait.new new_methods
  end

  def <<(list_of_symbols)
    original_name = list_of_symbols.first
    new_name = list_of_symbols.last
    trait = self - original_name
    trait.define_singleton_method(new_name,  &self.method(original_name))
    trait
  end
end


class ConflictMethodError < StandardError
  def initialize(msg = "Can't execute the message cause has conflict")
    super
  end
end