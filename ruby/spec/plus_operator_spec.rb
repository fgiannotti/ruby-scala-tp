  describe '+ operator' do

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
