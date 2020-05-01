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
        uses MyTrait
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

      pato1.pato
    end
  end
end