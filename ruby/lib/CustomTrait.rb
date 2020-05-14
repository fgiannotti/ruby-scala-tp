class CustomTrait

  def initialize(new_methods = Hash.new)
    new_methods.each do |met, block|
      define_singleton_method(met, block)
    end
  end

  def +(another_trait, strategy = DefaultStrategy.new)
    new_methods = Hash.new
    my_methods = self.methods(false)
    another_methods = another_trait.methods(false)
    conflict_method_names = my_methods & another_methods
    [self, another_trait].each { |trait|
      (trait.methods(false)).map { |met|
        proc = if conflict_method_names.include? met
                 strategy.resolve_conflict(method(met), another_trait.method(met))
               else
                 Proc.new { |*args| trait.method(met).call(*args) }
               end
        new_methods[met] = proc
      }
    }
    CustomTrait.new new_methods
  end

  def -(method)
    final_methods = Hash.new
    methods(false).select { |m| !m.equal? method }.each { |m| final_methods[m] = Proc.new {method(m)}}
    CustomTrait.new final_methods
  end

  def <<(list_of_symbols)
    original_name = list_of_symbols.first
    new_name = list_of_symbols.last
    trait = self - original_name
    trait.define_singleton_method(new_name, &self.method(original_name))
    trait
  end

end

class DefaultStrategy
  def resolve_conflict(trait_method, another_trait_method)
    Proc.new { raise ConflictMethodError.new }
  end
end

class OrderStrategy
  def resolve_conflict(trait_method, another_trait_method)
    Proc.new do |*args|
      val1 = trait_method.call(*args)
      val2 = another_trait_method.call(*args)
      "#{val1} \n#{val2}"
    end
  end
end

class BlockStrategy
  def initialize(block)
    @@block = block
  end
  def resolve_conflict(trait_method, another_trait_method)
    Proc.new do |*args|
      [trait_method.call(*args), another_trait_method.call(*args)].reduce("") {|block, block2| @@block.call(block, block2)}
    end
  end

end

class ConflictMethodError < StandardError
  def initialize(msg = "Can't execute the message cause has conflict")
    super
  end
end