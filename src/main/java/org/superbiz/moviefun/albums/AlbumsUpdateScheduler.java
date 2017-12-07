package org.superbiz.moviefun.albums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;

@Configuration
@EnableAsync
@EnableScheduling
public class AlbumsUpdateScheduler {

    private static final long SECONDS = 1000;
    private static final long MINUTES = 60 * SECONDS;

    private final AlbumsUpdater albumsUpdater;
    private final JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AlbumsUpdateScheduler(AlbumsUpdater albumsUpdater, DataSource dataSource) {
        this.albumsUpdater = albumsUpdater;
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Scheduled(initialDelay = 15 * SECONDS, fixedRate = 2 * MINUTES)
    public void run() {
        try {
            logger.debug("Starting albums update");

            if (startAlbumSchedulerTask(currentTime)) { //get current timestamp here
                logger.debug("Starting albums update");
                albumsUpdater.update();
                logger.debug("Finished albums update");

            } else {
                logger.debug("Nothing to start");
            }

            logger.debug("Finished albums update");

        } catch (Throwable e) {
            logger.error("Error while updating albums", e);
        }
    }

    private boolean startAlbumSchedulerTask(currentTime) { //pass in timestamp
        String now = currentTime; //now timestamp
        String lastUpdated; //compare with SQL DB last timestamp (refer to .ddl)

        //compare timestamps for 2 min
        //return true if gretaer than 2 min
        return true;
    }
}