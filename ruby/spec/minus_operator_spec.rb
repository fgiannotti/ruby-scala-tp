describe CustomTrait do
  describe '- operator' do
    class TodoBienTodoLegal
      uses MyTrait + (MyOtherTrait - :method1)
    end

    todoLegal = TodoBienTodoLegal.new
    it "todoLegal.method1 should return Soy un patito" do
      expect(todoLegal.method1).to eq("Soy un patito")
    end

    class TodoBienTodoLegal
      uses MyTrait + (MyOtherTrait - :method1) - :method3
    end

    todoLegal = TodoBienTodoLegal.new

    it "todoLegal should not respond to method3" do
      expect(todoLegal.respond_to? :method3).to eq false
    end

    it "todoLegal should raise NoMethodError exception if method3 is called" do
      expect do
        todoLegal.method3
      end.to raise_error(NoMethodError)
    end
  end
end