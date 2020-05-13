describe 'OrderStrategy' do
  class ConOrderStrategy
    uses MyOtherTrait .+(MyTrait, OrderStrategy)
  end

  con_order_strategy = ConOrderStrategy.new
  it "con_order_strategy should respond to method1" do
    expect(con_order_strategy.respond_to? :method1).to be true
  end

  it "con_order_strategy should use both method1" do
    expect(con_order_strategy.method1).to  eq("kawuabonga \nSoy un patito")
  end
end
