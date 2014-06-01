package edu.univdhaka.iit.echo.service;

import java.util.List;

import edu.univdhaka.iit.echo.domain.Echo;
import edu.univdhaka.iit.echo.domain.UserAccount;
import edu.univdhaka.iit.echo.repository.EchoFactory;

public class EchoServiceImpl implements EchoService{

	@Override
    public boolean postEcho(String postedText, UserAccount postedBy) {
        System.out.println("postEcho() tweetText =" + postedText + " by " + postedBy.getFullName());

        Echo echo = new Echo();
        echo.setId(EchoFactory.getGeneratedTweetId());
        echo.setEcho(postedText);
        //echo.setGeoTimeStamp(new Date(0));
        echo.setPostedBy(postedBy);

        EchoFactory.getEchoList().add(echo);

        System.out.println("[ " + echo.getId() + " ] = \"" + echo.getEcho() + "\" has been posted on " + echo.getGeoTimeStamp().toString() + " by " + postedBy.getFullName());

        return true;
    }

    @Override
    public List<Echo> findAllEcho(UserAccount user) {
        System.out.println("findAllEcho() by " + user.getFullName());

        List<Echo> echos = EchoFactory.getEchoList();

        for (Echo echo : echos) {
            if (!echo.getPostedBy().equals(user)) {
                echos.remove(echo);
            }
        }
        return echos;
    }
}
