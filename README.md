## PoC of metaprogramming in ruby

Creation of [Traits](https://en.wikipedia.org/wiki/Trait_(computer_programming)#:~:text=In%20computer%20programming%2C%20a%20trait,the%20functionality%20of%20a%20class.), with custom `uses` keyword:

```ruby
    class ClassWithTraits
      uses MyTrait + MyOtherTrait
    end
```

This is more or less an import in C, adds the methods from those classes. With some extra operators like: <<, +, -.

Example of << operator

```ruby
describe '<< operator' do
  class WithAlias
    uses MyOtherTrait + (MyTrait << (:method1 >> :saludo))
  end

  with_alias = WithAlias.new
  it "with_alias should respond to saludo" do
    expect(with_alias.respond_to? :saludo).to be true
  end
end
```
