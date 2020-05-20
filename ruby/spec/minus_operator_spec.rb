  describe '- operator' do
    class TodoBienTodoLegal
      uses MyTrait + (MyOtherTrait - :method1)
    end

    todoLegal = TodoBienTodoLegal.new
    it "todoLegal.method1 should return Soy un patito" do
      expect(todoLegal.method1).to eq("Soy un patito")
    end

    class TodoBienTodoLegal2
      uses MyTrait + (MyOtherTrait - :method1) - :method3
    end

    todoLegal2 = TodoBienTodoLegal2.new

    it "todoLegal should not respond to method3" do
      expect(todoLegal2.respond_to? :method3).to eq false
    end

    it "todoLegal should raise NoMethodError exception if method3 is called" do
      expect do
        todoLegal2.method3
      end.to raise_error(NoMethodError)
    end
  end