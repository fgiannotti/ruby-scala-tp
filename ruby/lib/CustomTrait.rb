class CustomTrait
  attr_accessor :my_methods

  def initialize(new_methods = Hash.new)
    @my_methods = new_methods
  end

  def +(another_trait, strategy = DefaultStrategy.new)
    new_methods = Hash.new
    my_methods = @my_methods
    another_methods = another_trait.my_methods
    conflict_method_names = my_methods.select { |met, _| another_methods.include? met }
    my_methods.merge(another_methods).each { |met, block_met|
      proc = if conflict_method_names.include? met
               strategy.resolve_conflict(self, another_trait, met)
             else
               Proc.new { |*args| block_met.call(*args) }
             end
      new_methods[met] = proc
    }
    CustomTrait.new new_methods
  end

  def -(method)
    final_methods = Hash.new
    methods(false).select { |m| !m.equal? method }.each { |m| final_methods[m] = Proc.new { method(m) } }
    CustomTrait.new final_methods
  end

  def <<(list_of_symbols)
    original_name = list_of_symbols.first
    new_name = list_of_symbols.last
    trait = self - original_name
    trait.my_methods[new_name] = my_methods[original_name]
    trait
  end
end

class DefaultStrategy
  def resolve_conflict(trait, another_trait, sym_method)
    Proc.new { raise ConflictMethodError.new }
  end
end

class OrderStrategy
  def resolve_conflict(trait, another_trait, sym_method)
    Proc.new do |*args|
      trait.my_methods[sym_method].call(*args)
      another_trait.my_methods[sym_method].call(*args)
    end
  end
end

class BlockStrategy
  def initialize(conflict_methods)
    @conflict_methods = conflict_methods
  end

  def resolve_conflict(trait, another_trait, sym_method)
    Proc.new do |*args|
      strategy = @conflict_methods.find { |conflict_met, _| conflict_met == sym_method }.last
      [trait.my_methods[sym_method].call(*args), another_trait.my_methods[sym_method].call(*args)].reduce { |arg1, arg2| strategy.call(arg1, arg2) }
    end
  end
end

class ConditionStrategy
  def initialize(conflict_methods)
    @conflict_methods = conflict_methods
  end

  def resolve_conflict(trait, another_trait, sym_method)
    Proc.new do |*args|
      conflicting_method = @conflict_methods.select { |conflict_met, _| conflict_met == sym_method }
      proc = [trait.my_methods[sym_method], another_trait.my_methods[sym_method]]
                 .select { |met| conflicting_method[sym_method].call(met.call(*args)) }.first
      if proc == nil
        raise NoMatchError
      else
        proc.call(*args)
      end
    end
  end
end

class NoMatchError < StandardError
  def initialize(msg = "No condition matched")
    super
  end
end

class ConflictMethodError < StandardError
  def initialize(msg = "Can't execute the message cause has conflict")
    super
  end
end