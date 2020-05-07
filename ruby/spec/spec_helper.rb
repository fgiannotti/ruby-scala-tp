require_relative '../lib/Trait'

Trait.define do
  module_name :MyOtherTrait
  module_method :method1 do
    "kawuabonga"
  end
  module_method :method3 do
    "Hello world"
  end
end
Trait.define do
  module_name :MyTrait
  module_method :method1 do
    "Soy un patito"
  end
  module_method :method2 do |numb|
    numb * 0+42
  end
end
