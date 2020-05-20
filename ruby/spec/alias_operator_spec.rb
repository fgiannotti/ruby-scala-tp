describe '<< operator' do
  class ConAlias
    uses MyOtherTrait + (MyTrait << (:method1 >> :saludo))
  end

  con_alias = ConAlias.new
  it "con_alias should respond to saludo" do
    expect(con_alias.respond_to? :saludo).to be true
  end
  it "con_alias should use MyTrait alias method" do
    expect(con_alias.saludo).to eq("Soy un patito")
  end
  it "con_alias should respond to method1" do
    expect(con_alias.respond_to? :method1).to be true
  end
  it "con_alias should use MyOtherTrait method1" do
    expect(con_alias.method1).to eq("kawuabonga")
  end
end
