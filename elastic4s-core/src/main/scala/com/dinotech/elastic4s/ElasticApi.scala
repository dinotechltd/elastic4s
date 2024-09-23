package com.dinotech.elastic4s

import com.dinotech.elastic4s.admin.IndexAdminApi
import com.dinotech.elastic4s.alias.AliasesApi
import com.dinotech.elastic4s.analyzers.{AnalyzerApi, NormalizerApi, TokenFilterApi, TokenizerApi}
import com.dinotech.elastic4s.bulk.BulkApi
import com.dinotech.elastic4s.cat.CatsApi
import com.dinotech.elastic4s.cluster.ClusterApi
import com.dinotech.elastic4s.count.CountApi
import com.dinotech.elastic4s.delete.DeleteApi
import com.dinotech.elastic4s.explain.ExplainApi
import com.dinotech.elastic4s.get.GetApi
import com.dinotech.elastic4s.indexes.admin.{ForceMergeApi, IndexRecoveryApi}
import com.dinotech.elastic4s.indexes.{CreateIndexApi, DeleteIndexApi, IndexApi, IndexTemplateApi}
import com.dinotech.elastic4s.locks.LocksApi
import com.dinotech.elastic4s.mappings.MappingApi
import com.dinotech.elastic4s.mappings.dynamictemplate.DynamicTemplateApi
import com.dinotech.elastic4s.nodes.NodesApi
import com.dinotech.elastic4s.reindex.ReindexApi
import com.dinotech.elastic4s.script.ScriptApi
import com.dinotech.elastic4s.searches._
import com.dinotech.elastic4s.searches.aggs.AggregationApi
import com.dinotech.elastic4s.searches.aggs.pipeline.PipelineAggregationApi
import com.dinotech.elastic4s.searches.collapse.CollapseApi
import com.dinotech.elastic4s.searches.queries.funcscorer.ScoreApi
import com.dinotech.elastic4s.searches.sort.SortApi
import com.dinotech.elastic4s.searches.suggestion.SuggestionApi
import com.dinotech.elastic4s.settings.SettingsApi
import com.dinotech.elastic4s.snapshots.SnapshotApi
import com.dinotech.elastic4s.task.TaskApi
import com.dinotech.elastic4s.termvectors.TermVectorApi
import com.dinotech.elastic4s.update.UpdateApi
import com.dinotech.elastic4s.validate.ValidateApi

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

// contains all the syntactic definitions
trait ElasticApi
    extends ElasticImplicits
    with AliasesApi
    with AggregationApi
    with AnalyzerApi
    with BulkApi
    with CatsApi
    with CreateIndexApi
    with ClusterApi
    with CollapseApi
    with CountApi
    with DeleteApi
    with DeleteIndexApi
    with DynamicTemplateApi
    with ExistsApi
    with ExplainApi
    with ForceMergeApi
    with GetApi
    with HighlightApi
    with IndexApi
    with IndexAdminApi
    with IndexRecoveryApi
    with IndexTemplateApi
    with LocksApi
    with MappingApi
    with NodesApi
    with NormalizerApi
    with QueryApi
    with PipelineAggregationApi
    with ReindexApi
    with ScriptApi
    with ScoreApi
    with ScrollApi
    with SearchApi
    with SearchTemplateApi
    with SettingsApi
    with SnapshotApi
    with SortApi
    with SuggestionApi
    with TaskApi
    with TermVectorApi
    with TokenizerApi
    with TokenFilterApi
    with TypesApi
    with UpdateApi
    with ValidateApi {

  implicit class RichFuture[T](future: Future[T]) {
    def await(implicit duration: Duration = 60.seconds): T = Await.result(future, duration)
  }
}

object ElasticApi extends ElasticApi
