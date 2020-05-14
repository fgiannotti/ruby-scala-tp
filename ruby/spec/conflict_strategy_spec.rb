describe 'OrderStrategy' do
  class ConOrderStrategy
    uses MyOtherTrait .+(MyTrait, OrderStrategy.new)
  end

  con_order_strategy = ConOrderStrategy.new
  it "con_order_strategy should respond to method1" do
    expect(con_order_strategy.respond_to? :method1).to be true
  end

  it "con_order_strategy should use both method1" do
    expect(con_order_strategy.method1).to  eq("kawuabonga \nSoy un patito")
  end
end

describe 'BlockStrategy' do
  class ConBlockStrategy
    uses MyOtherTrait .+(MyTrait, BlockStrategy.new(Proc.new {|arg1, arg2| arg1 << arg2 }))
  end

  con_block_strategy = ConBlockStrategy.new
  it "con_block_strategy should respond to method1" do
    expect(con_block_strategy.respond_to? :method1).to be true
  end

  it "con_block_strategy should use block with results of method1" do
    expect(con_block_strategy.method1).to  eq("kawuabongaSoy un patito")
  end
end
