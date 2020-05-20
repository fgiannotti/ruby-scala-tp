describe Trait do
  describe '.define' do

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
end