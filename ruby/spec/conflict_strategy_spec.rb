describe 'OrderStrategy' do
  class ConOrderStrategy
    uses MyOtherTrait .+(MyTrait, OrderStrategy.new)
  end

  con_order_strategy = ConOrderStrategy.new
  it "con_order_strategy should respond to method1" do
    expect(con_order_strategy.respond_to? :method5).to be true
  end

  it "con_order_strategy should use both method1" do
    class Patito
      attr_accessor :plumas
      attr_accessor :pico
    end
    patito = Patito.new
    con_order_strategy.method5 patito
    expect(patito.plumas).to  eq(58)
    expect(patito.pico).to eq("lindo")
  end
end

describe 'BlockStrategy' do
  class ConBlockStrategy
    uses MyOtherTrait .+(MyTrait, BlockStrategy.new(
        { :method1 => Proc.new { |arg1, arg2| arg1 << arg2 }, :method4 => Proc.new { |arg1, arg2| arg1 + arg2 } }
    ))
  end

  con_block_strategy = ConBlockStrategy.new
  it "con_block_strategy should respond to method1" do
    expect(con_block_strategy.respond_to? :method1).to be true
  end

  it "con_block_strategy should use block with results of method1" do
    expect(con_block_strategy.method1).to  eq("kawuabongaSoy un patito")
  end

  it "con_block_strategy should respond to method4" do
    expect(con_block_strategy.respond_to? :method4).to be true
  end

  it "con_block_strategy should use block with results of method1" do
    expect(con_block_strategy.method4).to  eq(9)
  end
end

describe 'ConditionStrategy' do
  class ConConditionStrategy
    uses MyOtherTrait .+(MyTrait, ConditionStrategy.new({:method4 => Proc.new {|arg| arg == 5 }, :method1 => Proc.new {|arg| arg.length == 12 }}))
  end

  con_condition_strategy = ConConditionStrategy.new
  it "con_condition_strategy should respond to method1" do
    expect(con_condition_strategy.respond_to? :method1).to be true
  end

  it "con_condition_strategy should use block with results of method1" do
    expect do
      con_condition_strategy.method1
      end.to raise_error(NoMatchError)
  end

  it "con_condition_strategy should respond to method4" do
    expect(con_condition_strategy.respond_to? :method4).to be true
  end

  it "con_condition_strategy should use block with results of method1" do
    expect(con_condition_strategy.method4).to  eq(5)
  end
end
