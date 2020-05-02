describe Trait do
  let(:prueba) { Trait.new }

  describe '.define' do
      Trait.define do
        module_name :MyTrait
        method :method1 do
           "Soy un patito"
        end
        method :method2 do |number|
          number * 0+42
        end
      end
      it 'add new Trait, MyTrait can respond to :method1' do
        expect(MyTrait.respond_to? :method1).to be true
      end

      it 'My Trait can respond :method2' do
        expect(CustomTrait.respond_to? :method2).to be false
      end

  end
=begin
  describe 'uses My Trait' do
      class MyClass
        uses MyTrait
        def method1
          "Hello world"
        end
      end
      example = MyClass.new
      it 'example should respond to: method1' do
        expect(example.respond_to? :method1).to be true
      end
      it 'example should respond to: method2' do
        expect(example.respond_to? :method2).to be true
      end
      it 'example.method1 should use MyClass method1' do
        expect(example.method1).to eq("Hello world")
      end

      it 'example.method2 should use MyTrait method2' do
        expect(example.method2(1)).to eq(42)
      end

  end
=end
end


