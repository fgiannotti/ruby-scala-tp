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

      puts Class.respond_to? :uses

      class Pato
        uses MyTrait
      end

      puts Pato.respond_to? :uses

      pato1 = Pato.new
      puts pato1.respond_to? :pato

    end
  end
end