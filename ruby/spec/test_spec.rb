describe Trait do
  let(:prueba) { Trait.new }

  describe '#materia' do
    it 'debería pasar este test' do
      Trait.define do
        trait_name :MyTrait
        method :pato do
          puts "patito"
          end
      end

      Trait.define do
        trait_name :MySecondTrait
        method      :run do
          puts "run"
        end
      end

      puts Class.respond_to? :uses

      class Pato
        uses MyTrait + MySecondTrait
      end

      puts Pato.respond_to? :uses

      pato1 = Pato.new
      puts pato1.respond_to? :pato
      puts pato1.respond_to? :run
    end
  end
end