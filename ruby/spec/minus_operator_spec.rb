describe CustomTrait do
  describe '- operator' do
    class TodoBienTodoLegal
      uses MyTrait + (MyOtherTrait - :method1)
    end

    todoLegal = TodoBienTodoLegal.new
    it "todoLegal.method1 should return Soy un patito" do
      expect(todoLegal.method1).to eq("Soy un patito")
    end
  end
end