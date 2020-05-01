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
      pato1 = Pato.new

      puts "Class responds a uses:"
      puts Class.respond_to? :uses
      puts "Class Pato responds a uses:"
      puts Pato.respond_to? :uses

      puts "Trait responds pato:"
      puts MyTrait.respond_to? :pato
      puts "Class Pato responds a :pato :"
      puts Pato.respond_to? :pato
      puts "pato1 responds a pato:"
      puts pato1.respond_to? :pato
      puts "pato 1 responds a run"
      puts pato1.respond_to? :run

    end
  end
end