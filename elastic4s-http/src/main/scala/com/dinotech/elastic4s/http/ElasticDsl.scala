package com.dinotech.elastic4s.http

import com.dinotech.elastic4s.{ElasticApi, Show}
import com.dinotech.elastic4s.http.bulk.BulkHandlers
import com.dinotech.elastic4s.http.cat.CatHandlers
import com.dinotech.elastic4s.http.cluster.ClusterHandlers
import com.dinotech.elastic4s.http.count.CountHandlers
import com.dinotech.elastic4s.http.delete.DeleteHandlers
import com.dinotech.elastic4s.http.explain.ExplainHandlers
import com.dinotech.elastic4s.http.get.GetHandlers
import com.dinotech.elastic4s.http.index._
import com.dinotech.elastic4s.http.index.admin.IndexAdminHandlers
import com.dinotech.elastic4s.http.index.alias.IndexAliasHandlers
import com.dinotech.elastic4s.http.index.mappings.MappingHandlers
import com.dinotech.elastic4s.http.locks.LocksHandlers
import com.dinotech.elastic4s.http.nodes.NodesHandlers
import com.dinotech.elastic4s.http.reindex.ReindexHandlers
import com.dinotech.elastic4s.http.search.template.SearchTemplateHandlers
import com.dinotech.elastic4s.http.search.{SearchHandlers, SearchScrollHandlers}
import com.dinotech.elastic4s.http.settings.SettingsHandlers
import com.dinotech.elastic4s.http.snapshots.SnapshotHandlers
import com.dinotech.elastic4s.http.task.TaskHandlers
import com.dinotech.elastic4s.http.termvectors.TermVectorHandlers
import com.dinotech.elastic4s.http.update.UpdateHandlers
import com.dinotech.elastic4s.http.validate.ValidateHandlers
import com.sksamuel.exts.Logging

trait ElasticDsl
    extends ElasticApi
    with Logging
    with BulkHandlers
    with CatHandlers
    with CountHandlers
    with ClusterHandlers
    with DeleteHandlers
    with ExistsHandlers
    with ExplainHandlers
    with GetHandlers
    with IndexHandlers
    with IndexAdminHandlers
    with IndexAliasHandlers
    with IndexStatsHandlers
    with IndexTemplateHandlers
    with LocksHandlers
    with MappingHandlers
    with NodesHandlers
    with ReindexHandlers
    with RolloverHandlers
    with SearchHandlers
    with SearchTemplateHandlers
    with SearchScrollHandlers
    with SettingsHandlers
    with SnapshotHandlers
    with UpdateHandlers
    with TaskHandlers
    with TermVectorHandlers
    with ValidateHandlers {

  implicit class RichRequest[T](t: T) {
    def request(implicit handler: Handler[T, _]): ElasticRequest = handler.build(t)
    def show(implicit handler: Handler[T, _]): String            = Show[ElasticRequest].show(handler.build(t))
  }
}

object ElasticDsl extends ElasticDsl
