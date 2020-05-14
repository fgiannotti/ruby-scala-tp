require_relative '../lib/Trait'

Trait.define do
  trait_name :MyOtherTrait
  trait_method :method1 do
    "kawuabonga"
  end
  trait_method :method3 do
    "Hello world"
  end
  trait_method :method4 do
    5
  end
end
Trait.define do
  trait_name :MyTrait
  trait_method :method1 do
    "Soy un patito"
  end
  trait_method :method4 do
    4
  end
  trait_method :method2 do |numb|
    numb * 0+42
  end
end
