/**
 * Copyright [2012] Anindya Bandopadhyay
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.andy.ds.nonlinear.graph.tree;

import com.andy.adt.TreeDataObject;

public class BinaryTree<T extends Comparable<T>> {
  private int numberOfNodes;
  private TreeDataObject<T> root;

  public TreeDataObject<T> insertNode(T data) {
    if (data == null) {
      throw new IllegalArgumentException("Null cannot be inserted. ");
    }
    TreeDataObject<T> node = null;
    if (getRoot() == null) {
      setRoot(insertRoot(data));
      node = getRoot();
    } else {
      TreeDataObject<T> parentNode = findParentNodeForNewNode(getRoot(), data);
      node = insertChild(parentNode, data);
    }
    if (node != null) {
      numberOfNodes++;
    }
    return node;
  }

  private TreeDataObject<T> insertChild(TreeDataObject<T> parentNode, T data) {
    TreeDataObject<T> child = null;
    if (data.compareTo(parentNode.getData()) <= 0) {
      child = insertLeftChild(parentNode, data);
    } else {
      child = insertRightChild(parentNode, data);
    }
    return child;
  }

  private TreeDataObject<T> insertLeftChild(TreeDataObject<T> parentNode, T data) {
    TreeDataObject<T> childNode = new TreeDataObject<T>();
    childNode.setData(data);
    parentNode.setLeftChildNode(childNode);
    childNode.setParentNode(parentNode);
    return childNode;
  }

  private TreeDataObject<T> insertRightChild(TreeDataObject<T> parentNode, T data) {
    TreeDataObject<T> childNode = new TreeDataObject<T>();
    childNode.setData(data);
    parentNode.setRightChildNode(childNode);
    childNode.setParentNode(parentNode);
    return childNode;
  }

  private TreeDataObject<T> insertRoot(T data) {
    TreeDataObject<T> rootNodeObject = new TreeDataObject<T>();
    rootNodeObject.setData(data);
    return rootNodeObject;
  }

  public TreeDataObject<T> deleteNode(T data) {
    if (data == null) {
      throw new IllegalArgumentException("Null cannot be deleted.");
    }
    TreeDataObject<T> toBeDeletednode = findNode(data);
    boolean isOk = ((toBeDeletednode != null) && (data.equals(toBeDeletednode.getData())));
    TreeDataObject<T> successorNode = null;
    if (isOk) {
      if ((toBeDeletednode.getLeftChildNode() == null) && (toBeDeletednode.getRightChildNode() == null)) {
        unlinkDeleteNodeLinkSuccessorNode(toBeDeletednode, null);
      } else if ((toBeDeletednode.getLeftChildNode() != null) && (toBeDeletednode.getRightChildNode() != null)) {
        successorNode = getMinValueNode(toBeDeletednode.getRightChildNode());

        updateSuccessorNodeChildrenParentRefBeforeShift(toBeDeletednode, successorNode);
        updateSuccessorNodeChildrensWithDeletedNodeChildrens(toBeDeletednode, successorNode);
        unlinkDeleteNodeLinkSuccessorNode(toBeDeletednode, successorNode);
      } else {
        if (toBeDeletednode.getLeftChildNode() != null) {
          successorNode = getMaxValueNode(toBeDeletednode.getLeftChildNode());
          updateSuccessorNodeChildrenParentRefBeforeShift(toBeDeletednode, successorNode);
        } else if (toBeDeletednode.getRightChildNode() != null) {
          successorNode = getMinValueNode(toBeDeletednode.getRightChildNode());
          updateSuccessorNodeChildrenParentRefBeforeShift(toBeDeletednode, successorNode);
        }
        updateSuccessorNodeChildrensWithDeletedNodeChildrens(toBeDeletednode, successorNode);
        unlinkDeleteNodeLinkSuccessorNode(toBeDeletednode, successorNode);
      }
      numberOfNodes--;
    }
    return successorNode;
  }

  protected void updateSuccessorNodeChildrenParentRefBeforeShift(TreeDataObject<T> toBeDeletednode,
          TreeDataObject<T> successorNode) {
    TreeDataObject<T> successorParentNode = successorNode.getParentNode();
    TreeDataObject<T> leftChildOfSuccessorNode = successorNode.getLeftChildNode();
    TreeDataObject<T> rightChildOfSuccessorNode = successorNode.getRightChildNode();
    if (successorParentNode != null && (!successorParentNode.equals(toBeDeletednode))) {
      successorParentNode.setLeftChildNode(rightChildOfSuccessorNode);
      if (rightChildOfSuccessorNode != null) {
        rightChildOfSuccessorNode.setParentNode(successorParentNode);
      }

      successorParentNode.setRightChildNode(leftChildOfSuccessorNode);
      if (leftChildOfSuccessorNode != null) {
        leftChildOfSuccessorNode.setParentNode(successorParentNode);
      }
    }
  }

  protected void updateSuccessorNodeChildrensWithDeletedNodeChildrens(TreeDataObject<T> deleteNode,
          TreeDataObject<T> successorNode) {
    successorNode.setParentNode(deleteNode.getParentNode());

    TreeDataObject<T> leftChildOfDeleteNode = deleteNode.getLeftChildNode();
    TreeDataObject<T> rightChildOfDeleteNode = deleteNode.getRightChildNode();

    if (!successorNode.equals(leftChildOfDeleteNode)) {
      successorNode.setLeftChildNode(leftChildOfDeleteNode);
    }

    if (leftChildOfDeleteNode != null) {
      leftChildOfDeleteNode.setParentNode(successorNode);
    }

    if (!successorNode.equals(rightChildOfDeleteNode)) {
      successorNode.setRightChildNode(rightChildOfDeleteNode);
    }
    if (rightChildOfDeleteNode != null) {
      rightChildOfDeleteNode.setParentNode(successorNode);
    }
  }

  private void unlinkDeleteNodeLinkSuccessorNode(TreeDataObject<T> deleteNode, TreeDataObject<T> successorNode) {
    deleteNode.setData(null);
    TreeDataObject<T> parentOfDeleteNode = deleteNode.getParentNode();
    if (parentOfDeleteNode != null) {
      if (deleteNode.equals(parentOfDeleteNode.getLeftChildNode())) {
        parentOfDeleteNode.setLeftChildNode(successorNode);
        if (successorNode != null) {
          successorNode.setParentNode(parentOfDeleteNode);
        }
      } else {
        parentOfDeleteNode.setRightChildNode(successorNode);
        if (successorNode != null) {
          successorNode.setParentNode(parentOfDeleteNode);
        }
      }
    } else {
      setRoot(successorNode);
    }
    deleteNode.setParentNode(null);
    deleteNode.setLeftChildNode(null);
    deleteNode.setRightChildNode(null);
  }

  private TreeDataObject<T> findParentNodeForNewNode(TreeDataObject<T> element, T data) {
    TreeDataObject<T> parentNode = null;
    while (element != null) {
      if (data.compareTo(element.getData()) <= 0) {
        parentNode = element;
        element = getLeftChild(element);
      } else {
        parentNode = element;
        element = getRightChild(element);
      }
    }
    return parentNode;
  }

  public TreeDataObject<T> findNode(T data) {
    TreeDataObject<T> element = getRoot();
    while (element != null && data != null) {
      int compairValue = data.compareTo(element.getData());
      if (compairValue < 0) {
        element = getLeftChild(element);
      } else if (compairValue > 0) {
        element = getRightChild(element);
      } else {
        return element;
      }
    }
    return null;
  }

  public int getNumberOfNodes() {
    return numberOfNodes;
  }

  public TreeDataObject<T> getRoot() {
    return root;
  }

  public void setRoot(TreeDataObject<T> root) {
    this.root = root;
  }

  public TreeDataObject<T> getLeftChild(TreeDataObject<T> node) {
    return node.getLeftChildNode();
  }

  public TreeDataObject<T> getRightChild(TreeDataObject<T> node) {
    return node.getRightChildNode();
  }

  public TreeDataObject<T> getMaxValueNode(TreeDataObject<T> node) {
    while (node != null) {
      if (getRightChild(node) == null) {
        break;
      } else {
        node = getRightChild(node);
      }
    }
    return node;
  }

  public TreeDataObject<T> getMinValueNode(TreeDataObject<T> node) {
    while (node != null) {
      if (getLeftChild(node) == null) {
        break;
      } else {
        node = getLeftChild(node);
      }

    }
    return node;
  }
}
