import com.atlassian.votingApp.controller.SomeController;
import com.atlassian.votingApp.models.Candidate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServiceIntegrationTests {

    SomeController controller = new SomeController();

    List<String> batchOfVotes = List.of("a" , "b" , "c");


    @Before
    public void setup(){

    }


    @Test
    public void testSomething(){
             List<String> batchOfVotes = List.of("a" , "b" , "c");
            controller.processBatchOfVotes(batchOfVotes);
            Map<String , Candidate> scores = controller.publishScore();

            Assert.assertEquals(3 ,  scores.entrySet().size());
            //Assert.assertEquals(java.util.Optional.of(1), scores.get("a"));
       // Assert.assertEquals(java.util.Optional.of(1), scores.get("b"));
        //Assert.assertEquals(java.util.Optional.of(1), scores.get("c"));

    }

    @Test
    public void testABatch(){
        List<String> batchOfVotes = List.of("a" , "b" , "c", "a" , "b" , "c","a" , "b" , "c");
        controller.processBatchOfVotes(batchOfVotes);
        Map<String , Candidate> scores = controller.publishScore();

        Assert.assertEquals(3 ,  scores.entrySet().size());

        Assert.assertEquals(3, scores.get("a").getScore().get());
        Assert.assertEquals(3, scores.get("b").getScore().get());
        Assert.assertEquals(3, scores.get("c").getScore().get());
    }


    @Test
    public void testABatchAndFindWinner(){
        List<String> batchOfVotes = List.of("a" , "b" , "c", "a" , "a" , "b", "b", "b","b","b");
        controller.processBatchOfVotes(batchOfVotes);

        Candidate mayBewinner =controller.publishWinner();

        Assert.assertEquals("b", mayBewinner.getName());


    }

    @Test
    public void sholdWorkFineWithEmptyInputs(){
        List<String> batchOfVotes = List.of();
        controller.processBatchOfVotes(batchOfVotes);
        Map<String , Candidate> scores = controller.publishScore();

        Assert.assertEquals(0 ,  scores.entrySet().size());

    }


   @Test
   public void shouldWorkFineWithMultiThreadedInputs() throws InterruptedException {
       List<String> batchOfVotes = List.of("a" , "b" , "c");

       ExecutorService executorService = Executors.newFixedThreadPool(10);

       for(int i =0 ; i<10 ; i++){
           executorService.execute(()-> controller.processBatchOfVotes(batchOfVotes));
       }

       Thread.sleep(100);
       Map<String , Candidate> scores=  controller.publishScore();

       Assert.assertEquals(3 ,  scores.entrySet().size());

       Assert.assertEquals(10, scores.get("a").getScore().get());
       Assert.assertEquals(10, scores.get("b").getScore().get());
       Assert.assertEquals(10, scores.get("c").getScore().get());

   }



    @Test
    public void testABatchAndFindWinnerWhenThereIsATie(){
        List<String> batchOfVotes = List.of("a" , "b" , "c", "a" ,  "b");
        controller.processBatchOfVotes(batchOfVotes);

        Candidate mayBewinner =controller.publishWinner();

        Assert.assertEquals("b", mayBewinner.getName());

    }







    @After
    public void resetSomething(){

    }

}
