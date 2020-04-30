describe Trait do
  let(:prueba) { Trait.new }

  describe '#materia' do
    it 'debería pasar este test' do
      Trait.define do
        module_name :MyTrait
        method :pato do
          puts "patito"
          end
      end

      class Pato
        include MyTrait
      end

      pato1 = Pato.new
      puts pato1.respond_to? :pato
    end
  end
end