describe Trait do
  let(:prueba) { Trait.new }

  describe '#materia' do
    it 'debería pasar este test' do
      Trait.new.define do
        module_name :MyTrait
        method :pato do
          puts "patito"
          end
      end
    end
  end
end