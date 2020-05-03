describe Trait do
  let(:prueba) { Trait.new }

  describe '.define' do
    Trait.define do
      module_name :MyTrait
      module_method :method1 do
        "Soy un patito"
      end
      module_method :method2 do |numb|
        numb * 0+42
      end
    end
    it 'add new Trait, MyTrait can respond to :method1' do
      expect(MyTrait.respond_to? :method1).to be true
    end

    it 'My Trait can respond :method2' do
      expect(CustomTrait.respond_to? :method2).to be false
    end

  end
  describe 'uses My Trait' do
    class MyClass
      uses MyTrait
      def method1
        "Hello world"
      end
    end

    example = MyClass.new
    it 'MyTrait should respond to: +' do
      expect(MyTrait.respond_to? :+).to be true
    end
    it 'example should respond to: method2' do
      expect(example.respond_to? :method2).to be true
    end
    it 'example.method1 should use MyClass method1' do
      expect(example.method1).to eq("Hello world")
    end

    it 'example.method2 should use MyTrait method2' do
      expect(example.method2(2)).to eq(42)
    end
  end


  describe '+ operator' do
    Trait.define do
      module_name :MyOtherTrait
      module_method :method1 do
        "kawuabonga"
      end
      module_method :method3 do
        "Hello world"
      end
    end
    class ConflictClass
      uses MyTrait + MyOtherTrait
    end

    conflict = ConflictClass.new
    it 'conflict should respond to: method2' do
      expect(conflict.respond_to? :method2).to be true
    end
    it 'conflict should respond to: method3' do
      expect(conflict.respond_to? :method3).to be true
    end
    it 'conflict.method3 should use MyOtherTrait method3' do
      expect(conflict.method3).to eq("Hello world")
    end

    it 'conflict.method2 should use MyTrait method2' do
      expect(conflict.method2(1)).to eq(42)
    end

    it 'conflict.method1 should throw exception' do
      expect{conflict.method1}.to raise_exception(ConflictMethodError)
    end

  end
end