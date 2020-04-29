describe Prueba do
  let(:prueba) { Trait.new }

  describe '#materia' do
    it 'debería pasar este test' do
      :prueba.define do
        nombre :MiTrait
      end
    end
  end
end