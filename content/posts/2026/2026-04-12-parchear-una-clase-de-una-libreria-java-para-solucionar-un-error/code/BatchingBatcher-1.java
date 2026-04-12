public class BatchingBatcher {

  ...

  private void checkRowCounts(int[] rowCounts, PreparedStatement ps) throws SQLException, HibernateException {
    int numberOfRowCounts = rowCounts.length;
    if (numberOfRowCounts != this.batchSize) {
      log.warn("JDBC driver did not return the expected number of row counts");
    }


    for(int i = 0; i < numberOfRowCounts; ++i) {
      this.expectations[i].verifyOutcome(rowCounts[i], ps, i);
    }
  }
}