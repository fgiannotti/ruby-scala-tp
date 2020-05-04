class CustomTrait

  def initialize(new_methods = Array.new)
    new_methods.each do |met|
      define_singleton_method(met.first, &met[1])
    end
  end

  def +(another_trait)
    methods_sum = [another_trait, self].flat_map { |trait| trait.methods(false).map { |m| [m, trait.method(m)] } }
    conflicting_methods = methods_sum.select { |m| methods_sum.count { |met| met.first.equal? m.first } > 1 }
    methods_that_raise_conflict_exception = conflicting_methods.uniq { |cm| cm.first }.map { |conflicting_method| [conflicting_method.first, Proc.new { raise ConflictMethodError }] }
    final_methods = methods_sum - conflicting_methods | methods_that_raise_conflict_exception
    CustomTrait.new final_methods
  end
end


class ConflictMethodError < StandardError
  def initialize(msg = "Can't execute the message cause has conflict")
    super
  end
end